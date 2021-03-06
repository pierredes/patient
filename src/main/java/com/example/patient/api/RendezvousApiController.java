package com.example.patient.api;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.patient.entities.PatientEntity;
import com.example.patient.entities.RendezvousEntity;
import com.example.patient.services.RendezvousService;

@RestController()
@RequestMapping("/ws/rdv")
public class RendezvousApiController {
	
	@Autowired
    RendezvousService rs;
	
	@GetMapping(path = "/", produces = "application/json")
    public List<RendezvousEntity> getallRDVApi(HttpServletRequest request) throws ParseException {

        return rs.getAllRdv(request.getParameter("search"));
    }
	
	@GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<RendezvousEntity> getRdvByIdApi(@PathVariable(name = "id") int id) {
        RendezvousEntity rendezvousGet = rs.getRdvById(id);
        if (rendezvousGet == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(rendezvousGet);
        }
    }
	
	@PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<RendezvousEntity> addRdvApi(@RequestBody RendezvousEntity rdv) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        String dateFormatStr = formatter.format( rdv.getDate() ); 
        try{
            RendezvousEntity createrdv = rs.addRdv( dateFormatStr,  rdv.getType(), rdv.getDuree() , rdv.getNote(), rdv.getPatient().getId() );

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createrdv.getId())
                    .toUri();

            return ResponseEntity.created(uri) // created => HTTP 201
                    .body(createrdv);

        }catch ( Exception e ){
            System.out.println(e.getMessage());
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }
	
	@PutMapping(path = "/update/{id}", produces = "application/json")
    public ResponseEntity<RendezvousEntity> updateRdvApi(@PathVariable(name = "id") int id, @RequestBody RendezvousEntity rdv) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        String dateFormatStr = formatter.format( rdv.getDate() ); 
        try{
        	RendezvousEntity rdvUpdate = rs.updateRdv(id, dateFormatStr, rdv.getType(), rdv.getDuree() , rdv.getNote(), rdv.getPatient().getId());
        	URI uir = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rdvUpdate).toUri();
            return ResponseEntity.created(uir).body(rdvUpdate);

        }catch ( Exception e ){
            System.out.println(e.getMessage());
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteRdv(@PathVariable(name = "id")int id) {
        try {
            rs.deleteRdv(id);
            return ResponseEntity.ok()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
