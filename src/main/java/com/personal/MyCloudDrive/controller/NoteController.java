package com.personal.MyCloudDrive.controller;

import com.personal.MyCloudDrive.model.Note;
import com.personal.MyCloudDrive.services.NoteService;
import com.personal.MyCloudDrive.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/delete-note/{noteid}")
    public String delete(@PathVariable("noteid") int noteid, Model model) {
        String error = null;

        try {
            noteService.deleteNote(noteid);
        } catch (Exception e) {
            error = e.toString();
        }

        displayMessage(model, "actionDelete", "actionFailed", "Your note", error);

        return "result";
    }

    @PostMapping("/add-note")
    public String add(@ModelAttribute Note note, Model model) {
        String error = null;

        if (note.getNoteId() == null) {
            note.setUserId(UserService.loggedinuser.getUserId());

            try {
                noteService.addNote(note);
            } catch (Exception e) {
                error = e.toString();
            }

            displayMessage(model, "actionSuccess", "actionFailed", "Your note", error);

        } else {
            try {
                noteService.updateNote(note);
            } catch (Exception e) {
                error = e.toString();
            }

            displayMessage(model, "actionUpdated", "actionFailed", "Your note", error);
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
