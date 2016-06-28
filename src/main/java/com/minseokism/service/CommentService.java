package com.minseokism.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.minseokism.repository.CommentRepository;

@Service
public class CommentService implements CommentRepository {

	@Override
	public List<com.minseokism.domain.Comment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.minseokism.domain.Comment> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.minseokism.domain.Comment> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends com.minseokism.domain.Comment> List<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends com.minseokism.domain.Comment> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<com.minseokism.domain.Comment> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public com.minseokism.domain.Comment getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<com.minseokism.domain.Comment> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends com.minseokism.domain.Comment> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.minseokism.domain.Comment findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(com.minseokism.domain.Comment entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends com.minseokism.domain.Comment> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
