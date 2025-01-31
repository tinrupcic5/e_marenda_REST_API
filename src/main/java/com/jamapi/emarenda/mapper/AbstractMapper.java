package com.jamapi.emarenda.mapper;

public interface AbstractMapper<M, E> {
  M toModel(E entity);

  E toEntity(M model);
}