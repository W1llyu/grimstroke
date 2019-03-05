package com.ouresports.grimstroke.im.controller.admin;

import com.ouresports.grimstroke.base.controller.BaseCrudController;
import com.ouresports.grimstroke.base.entity.BaseEntity;
import com.ouresports.grimstroke.base.service.Service;

/**
 *
 * @author will
 * @date 2018/12/5
 */
public abstract class BaseController<T extends BaseEntity, S extends Service<T>> extends BaseCrudController<T, S> {
}
