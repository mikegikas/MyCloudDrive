package com.personal.MyCloudDrive.services;

import com.personal.MyCloudDrive.mapper.CredentialMapper;
import com.personal.MyCloudDrive.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    public List<Credential> getCredentialbyUserId(int userId) {
        return credentialMapper.getCredentialByUserId(userId);
    }

    public Credential getCredintialById(int credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }

    public String getDecryptCredential(int credentialId) {
        Credential credential = getCredintialById(credentialId);
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public void addCredential(Credential credential) {
        credential.setUserId(UserService.loggedinuser.getUserId());
        credential.setKey(UserService.loggedinuser.getSalt());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        credentialMapper.addCredential(new Credential(null, credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(), credential.getUserId()));
    }

    public void updateCredential(Credential credential) {
        credential.setKey(UserService.loggedinuser.getSalt());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        credentialMapper.updateCredential(credential);
    }

    public void deleteCredential(int credentialId) {
        credentialMapper.deleteCredentialByID(credentialId);
    }
}
