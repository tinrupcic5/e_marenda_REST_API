package com.teapot.emarenda.mapper;

public interface AbstractMapper<M, E> {
  M toModel(E entity);

  E toEntity(M model);
}