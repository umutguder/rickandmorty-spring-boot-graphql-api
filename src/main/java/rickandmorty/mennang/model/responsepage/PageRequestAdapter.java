package rickandmorty.mennang.model.responsepage;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/*
PageRequestAdapter: Page request adapter for the documentation
The baseline RickAndMorty api documentation behaves as the first page for all page requests < 2
 */
public class PageRequestAdapter extends PageRequest {

    protected PageRequestAdapter(int page, int size, Sort sort) {
        super(page < 1 ? 0 : page - 1, size, sort);
    }

    public static PageRequest of(int page, int size) {
        return PageRequest.of(page < 1 ? 0 : page - 1, size);
    }
}
