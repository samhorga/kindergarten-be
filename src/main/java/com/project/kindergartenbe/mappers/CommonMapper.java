package com.project.kindergartenbe.mappers;

import com.project.kindergartenbe.model.be.AdultBE;
import com.project.kindergartenbe.model.be.AllergyBE;
import com.project.kindergartenbe.model.be.NoteBE;
import com.project.kindergartenbe.model.be.VaccineBE;
import com.project.kindergartenbe.model.dos.AdultDO;
import com.project.kindergartenbe.model.dos.AllergyDO;
import com.project.kindergartenbe.model.dos.NoteDO;
import com.project.kindergartenbe.model.dos.VaccineDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommonMapper {
    public List<AdultBE> mapAdultsDOtoAdultsBE(List<AdultDO> adults) {
        return Objects.nonNull(adults) ? adults.stream().map(AdultBE::new).collect(Collectors.toList()) : null;
    }

    public List<AllergyBE> mapAllergiesDOtoAllergiesBE(List<AllergyDO> allergies) {
        return Objects.nonNull(allergies) ? allergies.stream().map(AllergyBE::new).collect(Collectors.toList()) : null;
    }

    public List<NoteBE> mapNotesDOtoNotesBE(List<NoteDO> notes) {
        return Objects.nonNull(notes) ? notes.stream().map(NoteBE::new).collect(Collectors.toList()) : null;
    }

    public Set<VaccineBE> mapVaccinesDOtoVaccinesBE(Set<VaccineDO> vaccines) {
        return Objects.nonNull(vaccines) ? vaccines.stream().map(VaccineBE::new).collect(Collectors.toSet()) : null;
    }

    public List<AdultDO> mapAdultsBEtoAdultsDO(List<AdultBE> adults) {
        return Objects.nonNull(adults) ? adults.stream().map(AdultDO::new).collect(Collectors.toList()) : null;
    }
}
