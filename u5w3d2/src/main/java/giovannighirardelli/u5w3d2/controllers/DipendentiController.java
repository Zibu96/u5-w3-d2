package giovannighirardelli.u5w3d2.controllers;

import giovannighirardelli.u5w3d2.entities.Dipendenti;
import giovannighirardelli.u5w3d2.exceptions.BadRequestException;
import giovannighirardelli.u5w3d2.payloads.NewDipendentiDTO;
import giovannighirardelli.u5w3d2.payloads.NewDipendentiResponseDTO;
import giovannighirardelli.u5w3d2.servicies.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;

    @GetMapping
    public Page<Dipendenti> getDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {

        return this.dipendentiService.getDipendenti(page, size, sortBy);
    }

    @GetMapping("/me")
    public Dipendenti getProfile(@AuthenticationPrincipal Dipendenti currentAuthenticatedDipendenti){
        return currentAuthenticatedDipendenti;
    }

    @PutMapping("/me")
    public Dipendenti updateProfile(@AuthenticationPrincipal Dipendenti currentAuthenticatedDipendenti, @RequestBody Dipendenti body){
        return this.dipendentiService.findByIdAndUpdate(currentAuthenticatedDipendenti.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Dipendenti currentAuthenticatedDipendenti){
        this.dipendentiService.findByIdAndDelete(currentAuthenticatedDipendenti.getId());
    }



    @GetMapping("/{dipendentiId}")
    public Dipendenti findById(@PathVariable UUID dipendentiId) {
        return this.dipendentiService.findById(dipendentiId);
    }

    @PutMapping("/{dipendentiId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Dipendenti findByIdAndUpdate(@PathVariable UUID dipendentiId, @RequestBody Dipendenti body){
        return this.dipendentiService.findByIdAndUpdate(dipendentiId, body);

    }

    @DeleteMapping("/{dipendentiId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendentiId){
        this.dipendentiService.findByIdAndDelete(dipendentiId);
    }

    @PatchMapping("/me/profilePic")
    public Dipendenti uploadProfilePic (@RequestParam("profilePic") MultipartFile image,@AuthenticationPrincipal Dipendenti currentAuthenticatedDipendenti) throws IOException {
        return this.dipendentiService.uploadImage(currentAuthenticatedDipendenti.getId(), image);
    }

}