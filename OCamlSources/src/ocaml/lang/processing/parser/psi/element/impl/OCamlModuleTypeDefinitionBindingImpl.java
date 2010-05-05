/*
 * OCaml Support For IntelliJ Platform.
 * Copyright (C) 2010 Maxim Manuylov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */

package ocaml.lang.processing.parser.psi.element.impl;

import com.intellij.lang.ASTNode;
import ocaml.lang.feature.resolving.NameType;
import ocaml.lang.feature.resolving.ResolvingBuilder;
import ocaml.lang.feature.resolving.impl.BaseOCamlResolvedReference;
import ocaml.lang.feature.resolving.util.OCamlDeclarationsUtil;
import ocaml.lang.processing.parser.ast.element.OCamlElementTypes;
import ocaml.lang.processing.parser.ast.util.OCamlASTTreeUtil;
import ocaml.lang.processing.parser.psi.OCamlElementVisitor;
import ocaml.lang.processing.parser.psi.OCamlPsiUtil;
import ocaml.lang.processing.parser.psi.element.OCamlModuleType;
import ocaml.lang.processing.parser.psi.element.OCamlModuleTypeDefinitionBinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Maxim.Manuylov
 *         Date: 23.05.2009
 */
public class OCamlModuleTypeDefinitionBindingImpl extends BaseOCamlResolvedReference implements OCamlModuleTypeDefinitionBinding {
    public OCamlModuleTypeDefinitionBindingImpl(@NotNull final ASTNode node) {
        super(node);
    }

    public void visit(@NotNull final OCamlElementVisitor visitor) {
        visitor.visitModuleTypeDefinitionBinding(this);
    }

    @Nullable
    public ASTNode getNameElement() {
        return OCamlASTTreeUtil.checkNodeType(getNode().getFirstChildNode(), OCamlElementTypes.MODULE_TYPE_NAME);
    }

    @NotNull
    public NameType getNameType() {
        return NameType.AnyCase;
    }

    @NotNull
    public String getDescription() {
        return "module type";
    }

    @Override
    public boolean processDeclarations(@NotNull final ResolvingBuilder builder) {
        return OCamlDeclarationsUtil.processDeclarationsInStructuredBinding(builder, this);
    }

    @Nullable
    public OCamlModuleType getExpression() {
        return OCamlPsiUtil.getLastChildOfType(this, OCamlModuleType.class);
    }

    @Nullable
    public OCamlModuleType getTypeExpression() {
        return null;
    }
}