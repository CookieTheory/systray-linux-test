package hr.linux.systray_linux;

import org.eclipse.swt.widgets.Tray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystrayLinuxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystrayLinuxApplication.class, args);
	}

	@Bean
	TrayIconManager systemTray(){
		TrayIconManager systemTray = new TrayIconManager();
		systemTray.init();
		return systemTray;
	}

}
