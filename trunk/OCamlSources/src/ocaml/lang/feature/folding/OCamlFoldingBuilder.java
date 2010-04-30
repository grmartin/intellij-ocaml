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

package ocaml.lang.feature.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import ocaml.lang.processing.lexer.token.OCamlTokenTypes;
import ocaml.lang.processing.parser.ast.element.OCamlElementTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim.Manuylov
 *         Date: 24.05.2009
 */
public class OCamlFoldingBuilder implements FoldingBuilder {
    @NotNull
    public FoldingDescriptor[] buildFoldRegions(@NotNull final ASTNode node, @NotNull final Document document) {
       final List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
       appendDescriptors(node, document, descriptors);
       return descriptors.toArray(new FoldingDescriptor[descriptors.size()]); 
    }

    private void appendDescriptors(@NotNull final ASTNode node, @NotNull final Document document, @NotNull final List<FoldingDescriptor> descriptors) {
        final ASTNode firstChildNode = node.getFirstChildNode();

        if (node.getElementType() == OCamlElementTypes.PARENTHESES && firstChildNode != null && firstChildNode.getElementType() == OCamlTokenTypes.BEGIN_KEYWORD) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.STRUCT_END_MODULE_EXPRESSION) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.SIG_END_MODULE_TYPE) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.OBJECT_CLASS_BODY_END_EXPRESSION) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.OBJECT_END_CLASS_BODY_TYPE) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.OBJECT_END_CLASS_EXPRESSION) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.COMMENT_BLOCK) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }
        else if (node.getElementType() == OCamlElementTypes.UNCLOSED_COMMENT) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = firstChildNode;
        while (child != null) {
            appendDescriptors(child, document, descriptors);
            child = child.getTreeNext();
        }
    }

    @Nullable
    public String getPlaceholderText(@NotNull final ASTNode node) {
        if (node.getElementType() == OCamlElementTypes.PARENTHESES) {
            final ASTNode firstChildNode = node.getFirstChildNode();
            return firstChildNode != null && firstChildNode.getElementType() == OCamlTokenTypes.BEGIN_KEYWORD ? "begin ... end" : null;
        }
        else if (node.getElementType() == OCamlElementTypes.STRUCT_END_MODULE_EXPRESSION) {
            return "struct ... end";
        }
        else if (node.getElementType() == OCamlElementTypes.SIG_END_MODULE_TYPE) {
            return "sig ... end";
        }
        else if (node.getElementType() == OCamlElementTypes.OBJECT_CLASS_BODY_END_EXPRESSION) {
            return "object ... end";
        }
        else if (node.getElementType() == OCamlElementTypes.OBJECT_END_CLASS_BODY_TYPE) {
            return "object ... end";
        }
        else if (node.getElementType() == OCamlElementTypes.OBJECT_END_CLASS_EXPRESSION) {
            return "object ... end";
        }
        else if (node.getElementType() == OCamlElementTypes.COMMENT_BLOCK) {
            return "(*...*)";
        }
        else if (node.getElementType() == OCamlElementTypes.UNCLOSED_COMMENT) {
            return "(*...";
        }
        return null;
    }

    public boolean isCollapsedByDefault(@NotNull final ASTNode node) {
        return false;
    }
}