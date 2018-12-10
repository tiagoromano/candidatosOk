package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Util {

public static final int TIMEOUT = 300;

/**
 *
 * @param Entidade
 * @return Var
 */
// Util
public static Var processCurriculum(Var Entidade) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    System.out.println(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("curriculum")).getObjectAsString());
    System.out.println(Var.valueOf(api.pdf.PdfManager.byteToText(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("curriculum")))).getObjectAsString());
    return Var.VAR_NULL;
   }
 }.call();
}

}

