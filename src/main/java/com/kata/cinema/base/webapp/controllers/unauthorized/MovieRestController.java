package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.dto.response.PageDto;
import com.kata.cinema.base.models.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.PaginationDtoService;
import java.util.HashMap;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MovieRestController {

  private final PaginationDtoService<ReviewResponseDto> paginationDtoService;

  @Autowired
  public MovieRestController(PaginationDtoService<ReviewResponseDto> paginationDtoService) {
    this.paginationDtoService = paginationDtoService;
  }

  @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}?itemsOnPage={itemsOnPage}&typeReview={typeReview}&reviewSortType={reviewSortType}")
  public PageDto<ReviewResponseDto> getPageReview(@PathVariable("id") Long id, @PathVariable("pageNumber") Integer pageNumber,
      @RequestParam("itemsOnPage") Integer itemsOnPage,
      @RequestParam(value = "typeReview", required = false) TypeReview typeReview,
      @RequestParam(value = "reviewSortType", defaultValue = "DATE_ASC") ReviewSortType reviewSortType){
    HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("id", id);
    parameters.put("typeReview", typeReview);
    parameters.put("reviewSortType", reviewSortType);
    return paginationDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
  }

}
