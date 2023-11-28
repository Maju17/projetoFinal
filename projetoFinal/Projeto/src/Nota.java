@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cor;

    private BigDecimal valor;

}
public interface NotaRepository extends JpaRepository<Nota, Long> {
}

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> listarNotas() {
        return notaRepository.findAll();
    }

    public Nota adicionarNota(Nota nota) {
        return notaRepository.save(nota);
    }
}

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<List<Nota>> listarNotas() {
        List<Nota> nota = notaService.listarNotas();
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public ResponseEntity<Nota> adicionarNota(@RequestBody Nota nota) {
        Nota notaSalva = notaService.adicionarNota(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalva);
    }
}