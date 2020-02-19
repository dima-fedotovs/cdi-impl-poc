package guru.bug.cdipoc.apt;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;


@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class PoCCDIProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "NOTE: PROCESSOR IS WORKING");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "WARNING: PROCESSOR IS WORKING");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, "MWARN: PROCESSOR IS WORKING");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, "OTHER: PROCESSOR IS WORKING");
        System.out.println("STD oUT");
        return false;
    }
}
