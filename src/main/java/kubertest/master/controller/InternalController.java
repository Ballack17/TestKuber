package kubertest.master.controller;

import kubertest.master.data.dto.EssenceEmailOffDto;
import kubertest.master.service.EssenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/latest/internal/Master")
public class InternalController {

    private EssenceService essenceService;

    @Autowired
    public void setSomeEntityService(EssenceService essenceService) {
        this.essenceService = essenceService;
    }

    @GetMapping("/{entityId}")
    public EssenceEmailOffDto findById(@PathVariable UUID entityId) {
        return essenceService.findByIdEmailOffDto(entityId);
    }

}
