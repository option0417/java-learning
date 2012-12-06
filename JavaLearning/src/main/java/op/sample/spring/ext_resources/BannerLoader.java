package op.sample.spring.ext_resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class BannerLoader implements ResourceLoaderAware{
	private ResourceLoader bannerloader;
	private int num;

	@Override
	public void setResourceLoader(ResourceLoader _banner) {
		bannerloader = _banner;	
	}
	
	public void showBanner() throws IOException {
		Resource banner = bannerloader.getResource("file:resources/banner.txt");
		InputStream in = banner.getInputStream();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while(true) {
			String line = reader.readLine();
			if( line == null)
				break;
			System.out.println(line);
		}
		reader.close();
		num = 100;
	}
	
	public int getNum() {
		return num;
	}
	
	
}
