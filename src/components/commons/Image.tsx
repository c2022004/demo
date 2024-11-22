import React from 'react';
import { useState, forwardRef } from "react";

interface ImageProps extends React.ImgHTMLAttributes<HTMLImageElement> {
  src?: string;
  alt: string;
  fallBack?: string;
  sizes?: string;
}

const defaultFallbackImage = "https://example.com/default-image.png";

const Image = forwardRef<HTMLImageElement, ImageProps>(
  (
    {
      src,
      alt,
      sizes = "small",
      fallBack: customFallback = defaultFallbackImage,
      ...props
    },
    ref
  ) => {
    const [fallBack, setFallBack] = useState("");

    const handleError = () => {
      setFallBack(customFallback);
    };

    const classes = "overflow-hidden";

    return (
      <img
        className={classes}
        ref={ref}
        src={fallBack || src}
        alt={alt}
        {...props}
        onError={handleError}
      />
    );
  }
);

export default Image;
