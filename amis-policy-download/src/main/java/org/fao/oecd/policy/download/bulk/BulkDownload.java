package org.fao.oecd.policy.download.bulk;

import java.io.File;
import java.util.Map;

public interface BulkDownload {

    File createFile(File tmpFolder, Map<String, Object> parameters) throws Exception;
}
