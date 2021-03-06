package de.bonndan.nivio.output.graph;

import de.bonndan.nivio.landscape.Landscape;
import de.bonndan.nivio.landscape.LandscapeRepository;
import de.bonndan.nivio.output.dld4e.Dld4eRenderer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Controller
@RequestMapping(path = "/render")
public class GraphRenderController {

    private final LandscapeRepository landscapeRepository;

    @Autowired
    public GraphRenderController(LandscapeRepository landscapeRepository) {
        this.landscapeRepository = landscapeRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{landscape}/graph.png")
    public ResponseEntity<byte[]> pngResource(@PathVariable(name = "landscape") final String landscapeIdentifier) throws IOException {
        Landscape landscape = landscapeRepository.findDistinctByIdentifier(landscapeIdentifier);
        if (landscape == null)
            throw new EntityNotFoundException("Not found");

        GraphStreamRenderer graphStreamRenderer = new GraphStreamRenderer();
        File png = File.createTempFile(landscapeIdentifier, "png");
        graphStreamRenderer.render(landscape, png);

        byte[] bFile = Files.readAllBytes(png.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/png");
        return new ResponseEntity<>(
                bFile,
                headers,
                HttpStatus.OK
        );

    }


}
