package com.jamapi.emarenda.domain;

public interface AbstractMapper<M, E> {
  M toModel(E entity);

  E toEntity(M model);
}