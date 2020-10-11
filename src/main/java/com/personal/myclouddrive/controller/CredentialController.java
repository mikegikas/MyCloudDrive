package com.personal.myclouddrive.controller;

import com.personal.myclouddrive.model.Credential;
import com.personal.myclouddrive.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;


    @GetMapping("/delete-credential/{credentialid}")
    public String delete(@PathVariable("credentialid") int credentialid, Model model) {
        String error = null;
        try {
            credentialService.deleteCredential(credentialid);
        } catch (Exception e) {
            error = e.toString();
        }
        displayMessage(model, "actionDelete", "actionFailed", "Your credential", error);

        return "result";
    }

    @GetMapping("/decode-password/{credentialid}")
    public ResponseEntity<String> decodePassword(@PathVariable("credentialid") int credentialid) {
        return ResponseEntity.ok(credentialService.getDecryptCredential(credentialid));
    }

    @PostMapping("/add-credential")
    public String add(@ModelAttribute Credential credential, Model model) {
        String error = null;
        if (credential.getCredentialId() == null) {
            try {
                credentialService.addCredential(credential);
            } catch (Exception e) {
                error = e.toString();
            }
            displayMessage(model, "actionSuccess", "actionFailed", "Your credential", error);

        } else {
            try {
                credentialService.updateCredential(credential);
            } catch (Exception e) {
                error = e.toString();
            }
            displayMessage(model, "actionUpdated", "actionFailed", "Your credential", error);
        }

        return "result";
    }

    public void displayMessage(Model model, String actionSuccess, String actionFail, String sectionName, String error) {
        if (error == null) {
            model.addAttribute(actionSuccess, sectionName);
        } else {
            model.addAttribute(actionFail, error);
        }
    }
}
