package service.impl;

import models.Library;
import models.Reader;
import models.db.Database;
import service.ReaderService;

import java.util.ArrayList;

public class ReaderServiceImpl implements ReaderService {
    @Override
    public void saveReader(Reader reader) {
        Database.readers.add(reader);
    }

    @Override
    public ArrayList<Reader> getAllReaders() {
        return Database.readers;
    }

    @Override
    public Reader getReaderById(Long id) {
        for (Reader reader : Database.readers) {
            if (reader.getId() == id) {
                return reader;
            }
        }
        return null;
    }

    @Override
    public Reader updateReader(Long id, Reader reader) {
        for (Reader reader1 : Database.readers) {
            if (reader1.getId() == id) {
                reader1.setFullName(reader.getFullName());
                System.out.println("Susccessful");
                return reader1;
            }
        }return null;
    }

    @Override
    public void assignReaderToLibrary(Long readerId, Long libraryId) {
        for (Library library : Database.libraries) {
            if(library.getId() == libraryId){
                for (Reader reader : Database.readers) {
                    if (reader.getId() == readerId){
                        library.getReaders().add(reader);
                        System.out.println("succcesfull");
                    }
                }
            }
        }
    }
}
