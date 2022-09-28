package rickandmorty.mennang.model.responsepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfo<T> {
    private long count;
    private Integer pages;
    private Integer prev;
    private Integer next;

    public PageInfo(Integer currentPage, Page<T> page) {
        this.count = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.prev = currentPage >= 2 ? currentPage - 1 : null;
        this.next = currentPage < page.getTotalPages() ? currentPage + 1 : null;
    }
}
