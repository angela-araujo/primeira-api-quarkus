package acc.br;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import acc.br.model.Fruta;

@Path("/frutas")
public class FrutasResource {

    private static final Logger log = LoggerFactory.getLogger(FrutasResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
        log.info("***** Passei por aqui GET /frutas/");
        return Fruta.listAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void novaFruta(Fruta fruta) {
        log.info("***** Passei por aqui POST /frutas/");
        fruta.persist();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void excluirFruta(@PathParam("id") Long id) {
        log.info("***** Passei por aqui DELETE /frutas/{id}");
        Fruta frutaParaExcluir = Fruta.findById(id);
        if (frutaParaExcluir != null) {
            frutaParaExcluir.delete();
        }
    }
}
