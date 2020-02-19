package guru.bug.cdipoc.apt;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;


@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class PoCCDIProcessor extends AbstractProcessor {
    private boolean isCreated;
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("PROCESSING ANNOTATIONS " + roundEnv.getRootElements());
        if (isCreated) {
            return false;
        }
        isCreated = true;
        System.out.println("GENERATING FILE");
        try (var out = processingEnv.getFiler().createSourceFile("guru.bug.cdiproc.app.TestClass").openWriter()) {

            out.write("package guru.bug.cdiproc.app;\n");
            out.write("public class TestClass {\n");
            out.write("    public void sayHello() {\n");
            out.write("        System.out.println(\"Hello!\");\n");
            out.write("    }\n");
            out.write("}\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
