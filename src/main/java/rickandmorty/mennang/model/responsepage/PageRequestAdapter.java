package rickandmorty.mennang.model.responsepage;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageRequestAdapter extends PageRequest {

    protected PageRequestAdapter(int page, int size, Sort sort) {

        // All page < 2 values are considered as page = 0
        super(page < 1 ? 0 : page - 1, size, sort);
    }
}
