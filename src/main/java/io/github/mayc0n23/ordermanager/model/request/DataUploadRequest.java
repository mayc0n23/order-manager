package io.github.mayc0n23.ordermanager.model.request;

import io.github.mayc0n23.ordermanager.model.enums.Extension;
import io.github.mayc0n23.ordermanager.utils.annotation.ValidExtension;
import org.springframework.web.multipart.MultipartFile;

public record DataUploadRequest(
        @ValidExtension(allowedExtensions = {Extension.TXT})
        MultipartFile file) { }