package com.example.demos.presence;

import com.example.demos.product.Product;
import com.example.demos.product.ProductRequest;
import com.example.demos.product.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class PresenceMapper {
    public Presence toPresence(PresenceRequest request){
        return Presence.builder()
                .personalName(request.personalName())
                .type(request.type())
                .costs(request.costs())
                .company(request.company())
                .subtype(request.subtype())
                .presenceDescription(request.presenceDescription())
                .build();
    }

    public PresenceResponse toPresenceResponse(Presence presence) {
        return PresenceResponse.builder()
                .id(presence.getId())
                .personalName(presence.getPersonalName())
                .type(presence.getType())
                .costs(presence.getCosts())
                .company(presence.getCompany())
                .subtype(presence.getSubtype())
                .presenceDescription(presence.getPresenceDescription())
                .createdAt(presence.getCreatedAt())
                .build();
    }

    public void updatePresence(Presence presence, PresenceRequest request) {
        presence.setPersonalName(request.personalName());
        presence.setCosts(request.costs());
        presence.setCompany(request.company());
        presence.setSubtype(request.subtype());
        presence.setPresenceDescription(request.presenceDescription());
        presence.setType(request.type());
    }
}
