### springboot-mysql-jpa-thymeleaf-filter
springboot-mysql-jpa-thymeleaf-filter

### base on the curd and continue

### add query under ProductRespository interface

```
public interface ProductRepository extends JpaRepository<Product, Long> {
     
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) LIKE %?1%")
    public List<Product> search(String keyword);
}
```

### update Spring Service and Controller class

```
@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
     
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
     
    ...
}
```

```
@Controller
public class AppController {
    @Autowired
    private ProductService service;
     
    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = service.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
         
        return "index";
    }
     
    ...
}
```

### on thymeleaf index.html page add the following



```
<form th:action="@{/}">
    Filter: <input type="text" name="keyword" id="keyword" size="50" th:value="${keyword}" required />
    &nbsp;
    <input type="submit" value="Search" />
    &nbsp;
    <input type="button" value="Clear" id="btnClear" onclick="clearSearch()" />
</form>
```

```
<script type="text/javascript">
    function clearSearch() {
        window.location = "[[@{/}]]";
    }
</script>
```

use Thymeleaf expression [[@{/}]] to render the homepage URL properly.

### that is and run the app





