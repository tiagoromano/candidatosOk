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
    System.out.println(Entidade.getObjectAsString());
    return Var.VAR_NULL;
   }
 }.call();
}

}

