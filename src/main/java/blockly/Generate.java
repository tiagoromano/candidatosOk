package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Generate {

public static final int TIMEOUT = 300;

/**
 *
 * @param toConcat
 * @return Var
 */
// Generate
public static Var id2(Var toConcat) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    return Var.valueOf(toConcat.toString() + cronapi.util.Operations.random(Var.valueOf(99999)).toString());
   }
 }.call();
}

}

