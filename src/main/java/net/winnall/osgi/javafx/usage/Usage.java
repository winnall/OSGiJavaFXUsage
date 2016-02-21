package net.winnall.osgi.javafx.usage;

import net.winnall.osgi.javafx.JavaFXLauncher;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        service = Usage.class,
        immediate = true
)
public class Usage {

    private JavaFXLauncher javaFXLauncher;

    @Activate
    protected void activate() {
        javaFXLauncher.launch(HelloWorld.class);
    }

    @Reference
    protected void setJavaFXLauncher(JavaFXLauncher javaFXLauncher) {
        this.javaFXLauncher = javaFXLauncher;
    }

    protected void unsetJavaFXLauncher(JavaFXLauncher javaFXLauncher) {
        this.javaFXLauncher = null;
    }
}
