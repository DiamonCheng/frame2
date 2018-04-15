package com.dc.frame2.core.dto;

import com.dc.frame2.core.dao.conditions.ConditionsGroup;

/**
 * Base searcher object define. With no condition.
 * <p>Usage: extends it and  add condition fields with annotations. Annotations package : com.dc.frame2.core.dao.conditions
 *
 * @author DC
 * @date 2018/4/14.
 */
public class Searcher<T> implements ConditionsGroup{}
