package hr.linux.systray_linux;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import java.io.InputStream;

public class TrayIconManager {

    public void init(){
        new Thread(this::createTrayIcon).start();
    }

    public void createTrayIcon() {
        Display display = new Display();

        InputStream imageStream = TrayIconManager.class.getResourceAsStream("/img/arch-logo-150.png");
        if (imageStream == null) {
            throw new IllegalArgumentException("Image resource not found: /img/arch-logo-150.png");
        }
        Image image = new Image(display, imageStream);

        Tray tray = display.getSystemTray();
        if (tray != null) {
            TrayItem trayItem = new TrayItem(tray, SWT.NONE);
            trayItem.setImage(image);

            Shell shell = new Shell(display, SWT.NO_TRIM | SWT.APPLICATION_MODAL);
            shell.setVisible(false); // Hide the shell

            final Menu menu = new Menu(shell, SWT.POP_UP);
            MenuItem zatvori = new MenuItem(menu, SWT.PUSH);
            zatvori.setText("Zatvori");
            zatvori.addListener(SWT.Selection, e -> {
                image.dispose();
                display.dispose();
                System.exit(0);
            });

            trayItem.addListener(SWT.MenuDetect, e -> {
                menu.setLocation(display.getCursorLocation());
                menu.setVisible(true);
            });
        }

        // Event loop
        while (!display.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        image.dispose();
        display.dispose();
    }
}