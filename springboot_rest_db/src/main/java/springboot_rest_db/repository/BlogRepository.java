package springboot_rest_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot_rest_db.domain.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
