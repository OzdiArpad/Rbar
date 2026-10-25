package com.example.rbar_bcknd.UserRepository;

import com.example.rbar_bcknd.Entity.Product;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Érvényes és aktívan elérhető termékek lekérdezése. Bár okosabb volna talán  egy stater dátum is hozzá,
    //bár attól függ mire használjuk...
    @Query("SELECT u FROM Product u WHERE u.active=TRUE AND :now BETWEEN u.startDate AND u.expDate")
    List<Product> searchActiveProducts(@Param("now") LocalDate now);

    @Query("SELECT u FROM Product u WHERE u.active=TRUE AND :now BETWEEN u.startDate AND u.expDate AND u.productId=:productId")
    Product searchSingleProduct(@Param("now") LocalDate now, @Param("productId") Long productId);

    /* - Átmenetileg felfüggesztve. Frontend megkapja az adatot majd, de a lokáliss szúrést az végzi és eltárolja
       - ezzel az adatbázis lekérdezésének a gyakoriságát csökkentve.
        //Kategória szerinti lekérdezés
    @Query("SELECT u FROM Product u WHERE u.productType = :productType AND  u.active=:active AND :now BETWEEN u.startDate AND u.expDate")
    List<Product> searchByType(@Param("productType")String productType,@Param("active") boolean active, @Param("now") LocalDate now);

    //Search.
    @Query("SELECT u FROM Product u WHERE (u.name LIKE CONCAT('%', :search ,'%') OR u.description LIKE CONCAT('%', :search ,'%')) AND  u.active=:active AND :now BETWEEN u.startDate AND u.expDate")
    List<Product> searchByKeyword(@Parang servm("search") String search,@Param("active") boolean active,@Param("now") LocalDate now);
    */

    //Spring moduleok!!!
    //Mobile: Flacter
}
