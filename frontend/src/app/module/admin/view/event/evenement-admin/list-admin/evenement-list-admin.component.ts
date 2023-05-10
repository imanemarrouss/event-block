import {Component, OnInit} from '@angular/core';
import {EvenementService} from 'src/app/controller/service/Evenement.service';
import {EvenementDto} from 'src/app/controller/model/Evenement.model';
import {EvenementCriteria} from 'src/app/controller/criteria/EvenementCriteria.model';
import {AbstractListController} from 'src/app/zynerator/controller/AbstractListController';
import { environment } from 'src/environments/environment';

import { SalleService } from 'src/app/controller/service/Salle.service';
import { EvenementStateService } from 'src/app/controller/service/EvenementState.service';

import {EvenementStateDto} from 'src/app/controller/model/EvenementState.model';
import {SalleDto} from 'src/app/controller/model/Salle.model';
import {WebsocketService} from "../../../../../../controller/service/websocket.service";



@Component({
  selector: 'app-evenement-list-admin',
  templateUrl: './evenement-list-admin.component.html'
})
export class EvenementListAdminComponent extends AbstractListController<EvenementDto, EvenementCriteria, EvenementService>  implements OnInit {

    fileName = 'Evenement';
    evenements: any[] = [];
    salles :Array<SalleDto>;
    evenementStates :Array<EvenementStateDto>;
    websocketMessages: string[] = [];

    constructor(evenementService: EvenementService,private webSocketService: WebsocketService, private salleService: SalleService, private evenementStateService: EvenementStateService) {
        super(evenementService);

    }

    ngOnInit() : void {
      this.findPaginatedByCriteria();
      this.initExport();
      this.initCol();
      this.loadSalle();
      this.loadEvenementState();
        this.connectWebSocket();
    }

    public async loadEvenements(){
        await this.roleService.findAll();
        const isPermistted = await this.roleService.isPermitted('Evenement', 'list');
        isPermistted ? this.service.findAll().subscribe(evenements => this.items = evenements,error=>console.log(error))
        : this.messageService.add({severity: 'error', summary: 'erreur', detail: 'problème d\'autorisation'});
    }


    public initCol() {
        this.cols = [
            {field: 'reference', header: 'Reference'},
            {field: 'evenementStart', header: 'Evenement start'},
            {field: 'evenementEnd', header: 'Evenement end'},
            {field: 'salle?.reference', header: 'Salle'},
            {field: 'evenementState?.reference', header: 'Evenement state'},
        ];
    }


    public async loadSalle(){
        await this.roleService.findAll();
        const isPermistted = await this.roleService.isPermitted('Evenement', 'list');
        isPermistted ? this.salleService.findAllOptimized().subscribe(salles => this.salles = salles,error=>console.log(error))
        : this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Problème de permission'});
    }
    public async loadEvenementState(){
        await this.roleService.findAll();
        const isPermistted = await this.roleService.isPermitted('Evenement', 'list');
        isPermistted ? this.evenementStateService.findAllOptimized().subscribe(evenementStates => this.evenementStates = evenementStates,error=>console.log(error))
        : this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Problème de permission'});
    }

	public initDuplicate(res: EvenementDto) {
	}

   public prepareColumnExport() : void {
        this.exportData = this.items.map(e => {
            return {
                 'Reference': e.reference ,
                'Evenement start': this.datePipe.transform(e.evenementStart , 'dd/MM/yyyy hh:mm'),
                'Evenement end': this.datePipe.transform(e.evenementEnd , 'dd/MM/yyyy hh:mm'),
                'Salle': e.salle?.reference ,
                 'Description': e.description ,
                'Evenement state': e.evenementState?.reference ,
            }
        });

        this.criteriaData = [{
            'Reference': this.criteria.reference ? this.criteria.reference : environment.emptyForExport ,
            'Evenement start Min': this.criteria.evenementStartFrom ? this.datePipe.transform(this.criteria.evenementStartFrom , this.dateFormat) : environment.emptyForExport ,
            'Evenement start Max': this.criteria.evenementStartTo ? this.datePipe.transform(this.criteria.evenementStartTo , this.dateFormat) : environment.emptyForExport ,
            'Evenement end Min': this.criteria.evenementEndFrom ? this.datePipe.transform(this.criteria.evenementEndFrom , this.dateFormat) : environment.emptyForExport ,
            'Evenement end Max': this.criteria.evenementEndTo ? this.datePipe.transform(this.criteria.evenementEndTo , this.dateFormat) : environment.emptyForExport ,
        //'Salle': this.criteria.salle?.reference ? this.criteria.salle?.reference : environment.emptyForExport ,
            'Description': this.criteria.description ? this.criteria.description : environment.emptyForExport ,
        //'Evenement state': this.criteria.evenementState?.reference ? this.criteria.evenementState?.reference : environment.emptyForExport ,
        }];
      }
    /*private connectWebSocket() {
        this.webSocketService.connect(); // connect to the WebSocket server
        this.webSocketService.onMessage().subscribe((message: string) => {
            this.websocketMessages.push(message);
            // Handle the message received from the server
            // You can update the component's state or call a method to handle the message
        });
    }*/

    private connectWebSocket() {
        this.webSocketService.connect('ws://localhost:8036/api/admin/salle/${salleId}').subscribe((message: MessageEvent) => {
            this.websocketMessages.push(message.data);
            // Handle the message received from the server
            // You can update the component's state or call a method to handle the message
        });
    }


}
