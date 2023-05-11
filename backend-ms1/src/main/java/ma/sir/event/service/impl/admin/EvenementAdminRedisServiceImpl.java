package ma.sir.event.service.impl.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.sir.event.bean.core.Evenement;
import ma.sir.event.bean.core.EvenementRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EvenementAdminRedisServiceImpl {

    public static final String HASH_KEY = "Evenement";
    @Autowired
    private RedisTemplate template;

    /*public EvenementRedis save(EvenementRedis evenement){
        template.opsForHash().put(HASH_KEY + evenement.getId(), String.valueOf(evenement.getId()), evenement);

       // template.opsForHash().put(HASH_KEY,evenement.getId(),evenement);
        System.out.println(template);
        return evenement;
    }*/

    public EvenementRedis save(EvenementRedis evenement){

       // template.opsForHash().put(HASH_KEY, evenement.getReference(), evenement);
        template.opsForHash().put(HASH_KEY, String.valueOf(evenement.getReference()), evenement);
        return evenement;
    }


    public List<EvenementRedis> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }


    public EvenementRedis findByReference(String reference){
        return (EvenementRedis) template.opsForHash().get(HASH_KEY,reference);
    }


    /*public EvenementRedis findByReference(String reference){
        List<Object> events = template.opsForHash().values(HASH_KEY);
        for (Object event : events) {
            EvenementRedis evenement = (EvenementRedis) event;
            if (evenement.getReference().equals(reference)) {
                return evenement;
            }
        }
        return null;
    }*/






    public int deleteByReference(String reference){
         template.opsForHash().delete(HASH_KEY,reference);
        return 1;
    }
}
