/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.malisis.core.renderer.animation.transformation;

import java.util.ArrayList;

import net.malisis.core.renderer.element.Shape;

/**
 * @author Ordinastie
 * 
 */
public class ParallelTransformation extends Transformation<ParallelTransformation>
{
	protected ArrayList<Transformation> listTransformations = new ArrayList<>();

	public ParallelTransformation(Transformation... transformations)
	{
		addTransformations(transformations);

	}

	public ParallelTransformation addTransformations(Transformation... transformations)
	{
		for (Transformation transformation : transformations)
		{
			duration = Math.max(duration, transformation.totalDuration());
			listTransformations.add(transformation);
		}

		return this;
	}

	@Override
	protected void doTransform(Shape s, float comp)
	{
		if (listTransformations.size() == 0)
			return;

		for (Transformation transformation : listTransformations)
			transformation.transform(s, elapsedTimeCurrentLoop);
	}

	@Override
	public ParallelTransformation reversed(boolean reversed)
	{
		if (!reversed)
			return this;

		for (Transformation transformation : listTransformations)
			transformation.reversed(true);

		return this;
	}
}
