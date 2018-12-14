package com.ouresports.grimstroke.app.controller.admin;

import com.ouresports.grimstroke.app.base.controller.BaseCrudController;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.base.service.Service;

/**
 *
 * @author will
 * @date 2018/12/5
 */
public abstract class BaseController<T extends BaseEntity, S extends Service<T>> extends BaseCrudController<T, S> {
}
