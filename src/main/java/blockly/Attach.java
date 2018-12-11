package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Attach {

public static final int TIMEOUT = 300;

/**
 *
 * @param Entidade
 * @return Var
 */
// Attach
public static Var curriculum(Var Entidade) throws Exception {
 return new Callable<Var>() {

   private Var curriculumBytes = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;
   private Var curriculumText = Var.VAR_NULL;

   public Var call() throws Exception {
    cronapi.util.Operations.upload(Var.valueOf("Clique aqui para anexar o currículo"), Var.valueOf(".pdf"), Var.valueOf("20MB"), Var.valueOf("false"), (sender_item) -> {
        item = Var.valueOf(sender_item);
        curriculumBytes = cronapi.io.Operations.fileReadAllToBytes(cronapi.io.Operations.fileOpenToRead(cronapi.list.Operations.get(item, Var.valueOf(1))));
        curriculumText = Var.valueOf(api.pdf.PdfManager.byteToText(curriculumBytes));
        cronapi.database.Operations.execute(Var.valueOf("app.entity.User"), Var.valueOf("update User set curriculum = :curriculum, curriculumText = :curriculumText where id = :id"),Var.valueOf("curriculum",curriculumBytes),Var.valueOf("curriculumText",curriculumText),Var.valueOf("id",cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("id"))));
        System.out.println(curriculumText.getObjectAsString());
    });
    return Var.VAR_NULL;
   }
 }.call();
}

}

