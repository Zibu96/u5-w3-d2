package giovannighirardelli.u5w3d2.servicies;

import com.cloudinary.Cloudinary;
import giovannighirardelli.u5w3d2.entities.Dipendenti;
import giovannighirardelli.u5w3d2.entities.Dispositivi;
import giovannighirardelli.u5w3d2.enums.Stato;
import giovannighirardelli.u5w3d2.exceptions.BadRequestException;
import giovannighirardelli.u5w3d2.exceptions.NotFoundException;
import giovannighirardelli.u5w3d2.payloads.NewDispositiviDTO;
import giovannighirardelli.u5w3d2.repositories.DipendentiRepository;
import giovannighirardelli.u5w3d2.repositories.DispositiviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DispositiviService {

    @Autowired
    private DispositiviRepository dispositiviRepository;
    @Autowired
    private DipendentiService dipendentiService;


    public Page<Dispositivi> getDispositivi(int pageNumber, int pageSize, String sortBy){
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return dispositiviRepository.findAll(pageable);
    }

    public Dispositivi saveDispositivi(NewDispositiviDTO body) {


        Dispositivi dispositivi = new Dispositivi(body.tipologia(), body.stato(), null);


        return this.dispositiviRepository.save(dispositivi);

    }

    public Dispositivi findById(UUID id) {
        return this.dispositiviRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dispositivi findByIdAndUpdate(UUID id, Dispositivi updatedDispositivi) {
        Dispositivi found = this.findById(id);

        found.setTipologia(updatedDispositivi.getTipologia());
        found.setStato(updatedDispositivi.getStato());
        found.setDipendenti(updatedDispositivi.getDipendenti());

        return this.dispositiviRepository.save(found);

    }

    public void findByIdAndDelete(UUID id) {
        Dispositivi found = this.findById(id);
        this.dispositiviRepository.delete(found);

    }

    public Dispositivi uploadDipendenti(UUID dispositivoId, UUID dipendentiId){
        Dispositivi found = this.dispositiviRepository.findById(dispositivoId).orElseThrow(() -> new NotFoundException(dispositivoId));
        Dipendenti dipendenti = this.dipendentiService.findById(dipendentiId);
       found.setDipendenti(dipendenti);
       found.setStato(Stato.ASSEGNATO);


        return this.dispositiviRepository.save(found);
    }
}
