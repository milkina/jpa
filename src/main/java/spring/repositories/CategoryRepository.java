package spring.repositories;

import model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Query("SELECT c FROM Test t JOIN t.categories c " +
            "WHERE t.pathName=:testPath AND c.orderId>= " +
            "(SELECT c1.orderId FROM Category c1 WHERE c1.pathName=:categoryPath)" +
            " ORDER BY c.orderId,c.id")
    List<Category> getNextCategories(@Param("testPath") String testPath, @Param("categoryPath") String categoryPath);

    @Query("SELECT c FROM Test t JOIN t.categories c " +
            "WHERE t.pathName=:testPath AND c.orderId<= " +
            "(SELECT c1.orderId FROM Category c1 WHERE c1.pathName=:categoryPath)" +
            " ORDER BY c.orderId,c.id")
    List<Category> getPreviousCategories(@Param("testPath") String testPath, @Param("categoryPath") String categoryPath);

    Category findByPathName(String pathName);

    @Query("select c from Test t join t.categories c where t.pathName=:p order by c.orderId")
    List<Category> findCategoriesByTestPath(@Param("p") String pathName);

    @Query("select c from Category c join fetch c.tests where c.id in " +
            "(select cc.id from Category cc join cc.tests t group by cc having count(t)>1)")
    List<Category> getDuplicateCategories();
}
