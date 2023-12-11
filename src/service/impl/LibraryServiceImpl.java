package service.impl;

import models.Library;
import models.db.Database;
import service.LibraryService;

import java.util.ArrayList;

public class LibraryServiceImpl implements LibraryService {

    @Override
    public String saveLibrary(Library librarie) {
        Database.libraries.add(librarie);
        return "Succesful";
    }

    @Override
    public ArrayList<Library> getAllLibraries() {
        return Database.libraries;
    }

    @Override
    public Library getLibraryById(Long id) {
        for (Library library : Database.libraries) {
            if (library.getId() == id) {
                return library;
            }
        }
        return null;
    }

    @Override
    public Library updateLibrary(Long id, Library library) {
        for (int i = 0; i < Database.libraries.size(); i++) {
            if (Database.libraries.get(i).getId() == id) {
                Database.libraries.set(i, library);
                Database.libraries.get(i).setId(id);
                System.out.println("Successful updated ");
               return Database.libraries.get(i);
            }
        }
        return null;
    }

    @Override
    public String deleteLibrary(Long id) {
        for (Library library : Database.libraries) {
            if (library.getId() == id) {
                Database.libraries.remove(library);
                return "Succesfull";
            }
        }
        return "Not found id";
    }
}
