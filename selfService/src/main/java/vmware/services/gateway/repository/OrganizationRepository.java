package vmware.services.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vmware.services.gateway.entity.Organization;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
