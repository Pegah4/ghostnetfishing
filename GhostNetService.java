package de.iu.ghostnetfishing;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GhostNetService {

    private final GhostNetRepository ghostNetRepository;

    public GhostNetService(GhostNetRepository ghostNetRepository) {
        this.ghostNetRepository = ghostNetRepository;
    }

    public List<GhostNet> getAllGhostNets() {
        return ghostNetRepository.findAll().stream()
                .filter(ghostNet -> "Gemeldet".equals(ghostNet.getStatus())
                        || "Bergung bevorstehend".equals(ghostNet.getStatus()))
                .toList();
    }
    public GhostNet saveGhostNet(GhostNet ghostNet) {
        return ghostNetRepository.save(ghostNet);
    }

    public GhostNet getGhostNetById(Long id) {
        return ghostNetRepository.findById(id).orElse(null);
    }
}