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
import ocaml.lang.processing.parser.psi.OCamlElementVisitor;
import ocaml.lang.processing.parser.psi.element.OCamlStructuredElement;
import ocaml.lang.processing.parser.psi.element.OCamlTypeParameter;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim.Manuylov
 *         Date: 21.03.2009
 */
public class OCamlTypeParameterImpl extends BaseOCamlElement implements OCamlTypeParameter {
    public OCamlTypeParameterImpl(@NotNull final ASTNode node) {
        super(node);
    }

    public void visit(@NotNull final OCamlElementVisitor visitor) {
        visitor.visitTypeParameter(this);
    }

    @NotNull
    public List<OCamlStructuredElement> findActualDefinitions() {
        return Collections.<OCamlStructuredElement>singletonList(this);
    }
}