package org.aksw.rdfunit.io.reader;

import org.aksw.rdfunit.io.writer.RDFWriter;
import org.aksw.rdfunit.io.writer.RDFWriterException;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;

/**
 * <p>RDFReadAndCacheReader class.</p>
 *
 * @author Dimitris Kontokostas
 *         reads from a RDFReader and caches result
 * @since 11/14/13 1:09 PM
 * @version $Id: $Id
 */
public class RdfReadAndCacheReader implements RdfReader {
    private final RdfReader reader;
    private final RDFWriter writer;

    /**
     * <p>Constructor for RDFReadAndCacheReader.</p>
     *
     * @param reader a {@link RdfReader} object.
     * @param writer a {@link org.aksw.rdfunit.io.writer.RDFWriter} object.
     */
    public RdfReadAndCacheReader(RdfReader reader, RDFWriter writer) {
        super();
        this.reader = reader;
        this.writer = writer;
    }

    /** {@inheritDoc} */
    @Override
    public void read(Model model) throws RdfReaderException {
        reader.read(model);
        //If read succeeds try to write
        try {
            writer.write(model);
        } catch (RDFWriterException e) {

        }
    }

    /** {@inheritDoc} */
    @Override
    public void readDataset(Dataset dataset) throws RdfReaderException {
        reader.readDataset(dataset);
        //If read succeeds try to write
        try {
            //TODO change this
            writer.write(dataset.getDefaultModel());
        } catch (RDFWriterException e) {

        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "RDFReadAndCacheReader{" +
                "reader=" + reader +
                ", writer=" + writer +
                '}';
    }
}