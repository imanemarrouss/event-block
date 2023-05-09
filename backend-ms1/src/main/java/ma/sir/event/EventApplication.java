package  ma.sir.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.util.*;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.cache.annotation.EnableCaching;


import ma.sir.event.bean.core.*;
import ma.sir.event.service.facade.admin.*;

import ma.sir.event.zynerator.security.common.AuthoritiesConstants;
import ma.sir.event.zynerator.security.bean.User;
import ma.sir.event.zynerator.security.bean.Permission;
import ma.sir.event.zynerator.security.bean.Role;
import ma.sir.event.zynerator.security.service.facade.UserService;
import ma.sir.event.zynerator.security.service.facade.RoleService;


import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
//@EnableFeignClients("ma.sir.event.required.facade")
public class EventApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(EventApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService) {
    return (args) -> {
        if(true){

            createEvenementState();
            createSalle();
            createBlocOperatoir();


    // Role admin

        User userForAdmin = new User("admin");

        Role roleForAdmin = new Role();
        roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
        List<Permission> permissionsForAdmin = new ArrayList<>();
        addPermissionForAdmin(permissionsForAdmin);
        roleForAdmin.setPermissions(permissionsForAdmin);
        if(userForAdmin.getRoles()==null)
            userForAdmin.setRoles(new ArrayList<>());

        userForAdmin.getRoles().add(roleForAdmin);
        userService.save(userForAdmin);
            }
        };
    }



    private void createEvenementState(){
        String reference = "reference";
        String code = "code";
        List <String> status = Arrays.asList("programmer","en cours","cloturer");
        for (int i = 0; i < status.size(); i++) {
            EvenementState item = new EvenementState();
            item.setReference(status.get(i));
            item.setCode(status.get(i));
            evenementStateService.create(item);
        }
    }
    private void createSalle(){
        String reference = "salle";
        String code = "salle";
        for (int i = 1; i < 100; i++) {
            Salle item = new Salle();
            item.setReference(fakeString(reference, i));
            item.setCode(fakeString(code, i));
            salleService.create(item);
        }
    }
    private void createBlocOperatoir(){
        String reference = "blocOp";
        String code = "blocOp";
        for (int i = 1; i < 100; i++) {
            BlocOperatoir item = new BlocOperatoir();
            item.setReference(fakeString(reference, i));
            item.setCode(fakeString(code, i));
            blocOperatoirService.create(item);
        }
    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }
    private static void addPermissionForAdmin(List<Permission> permissions){
        permissions.add(new Permission("EvenementState.edit"));
        permissions.add(new Permission("EvenementState.list"));
        permissions.add(new Permission("EvenementState.view"));
        permissions.add(new Permission("EvenementState.add"));
        permissions.add(new Permission("EvenementState.delete"));
        permissions.add(new Permission("Evenement.edit"));
        permissions.add(new Permission("Evenement.list"));
        permissions.add(new Permission("Evenement.view"));
        permissions.add(new Permission("Evenement.add"));
        permissions.add(new Permission("Evenement.delete"));
        permissions.add(new Permission("Salle.edit"));
        permissions.add(new Permission("Salle.list"));
        permissions.add(new Permission("Salle.view"));
        permissions.add(new Permission("Salle.add"));
        permissions.add(new Permission("Salle.delete"));
        permissions.add(new Permission("BlocOperatoir.edit"));
        permissions.add(new Permission("BlocOperatoir.list"));
        permissions.add(new Permission("BlocOperatoir.view"));
        permissions.add(new Permission("BlocOperatoir.add"));
        permissions.add(new Permission("BlocOperatoir.delete"));
    }

    @Autowired
    EvenementStateAdminService evenementStateService;
    @Autowired
    SalleAdminService salleService;
    @Autowired
    BlocOperatoirAdminService blocOperatoirService;
}


