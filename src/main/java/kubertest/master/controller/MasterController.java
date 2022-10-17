package kubertest.master.controller;


import kubertest.master.data.dto.EssenceEmailOffDto;
import kubertest.master.data.dto.SystemDto;
import kubertest.master.service.EssenceService;
import kubertest.master.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/latest/web/Master")
public class MasterController {

    private final EssenceService essenceService;
    private final InfoService infoService;

    @PostMapping("/entity")
    public UUID createSomeEntity (@Valid @RequestBody EssenceEmailOffDto essenceEmailOffDto) {
        return essenceService.createSomeEntityWithoutId(essenceEmailOffDto);
    }

    @GetMapping("/{entityId}")
    public EssenceEmailOffDto findById(@PathVariable UUID entityId) {
        return essenceService.findByIdEmailOffDto(entityId);
    }

    @GetMapping("/info")
    public SystemDto getInfo() {
        return infoService.getInfo();
    }

}
