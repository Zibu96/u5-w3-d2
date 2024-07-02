package giovannighirardelli.u5w3d2.controllers;

import giovannighirardelli.u5w3d2.entities.Dispositivi;
import giovannighirardelli.u5w3d2.exceptions.BadRequestException;
import giovannighirardelli.u5w3d2.payloads.NewDispositiviDTO;
import giovannighirardelli.u5w3d2.payloads.NewDispositiviResponseDTO;
import giovannighirardelli.u5w3d2.servicies.DispositiviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dispositivi")
public class DispositiviController {

    @Autowired
    private DispositiviService dispositiviService;

    @GetMapping
    public Page<Dispositivi> getDispositivi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {

        return this.dispositiviService.getDispositivi(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  NewDispositiviResponseDTO saveDispositivi (@RequestBody @Validated NewDispositiviDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewDispositiviResponseDTO(this.dispositiviService.saveDispositivi(body).getId());
    }

    @GetMapping("/{dispositiviId}")
    public Dispositivi findById(@PathVariable UUID dispositiviId) {
        return this.dispositiviService.findById(dispositiviId);
    }

    @PutMapping("/{dispositiviId}")
    public Dispositivi findByIdAndUpdate(@PathVariable UUID dispositiviId, @RequestBody Dispositivi body){
        return this.dispositiviService.findByIdAndUpdate(dispositiviId, body);

    }

    @DeleteMapping("/{dispositiviId}")
    public void findByIdAndDelete(@PathVariable UUID dispositiviId){
        this.dispositiviService.findByIdAndDelete(dispositiviId);
    }

    @PatchMapping("/{dispositiviId}/{dipendentiId}")
    public Dispositivi assignDispositivi(@PathVariable UUID dispositiviId, @PathVariable UUID dipendentiId){
        return this.dispositiviService.uploadDipendenti(dispositiviId, dipendentiId);
    }
}

