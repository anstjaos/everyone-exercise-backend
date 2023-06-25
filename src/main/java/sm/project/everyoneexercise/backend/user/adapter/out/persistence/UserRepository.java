package sm.project.everyoneexercise.backend.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
}
