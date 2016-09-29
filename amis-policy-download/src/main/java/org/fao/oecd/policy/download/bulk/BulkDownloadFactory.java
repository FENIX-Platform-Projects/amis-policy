package org.fao.oecd.policy.download.bulk;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Iterator;

@ApplicationScoped
public class BulkDownloadFactory {
    @Inject Instance<BulkDownload> instance;

    public BulkDownload getInstance(String name) throws Exception {
        if (name!=null)
            for (Iterator<BulkDownload> instanceIterator = instance.select(BulkDownload.class).iterator(); instanceIterator.hasNext();) {
                BulkDownload bulkDownloadManager = instanceIterator.next();
                BulkName bulkName = bulkDownloadManager.getClass().getAnnotation(BulkName.class);
                if (bulkName.value().equals(name))
                    return bulkDownloadManager;
            }
        return null;
    }
}
