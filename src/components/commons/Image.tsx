import React from "react";
import { useState, forwardRef } from "react";
import defaultImage from "../../assets/img/defaultImage.jpg";
interface ImageProps extends React.ImgHTMLAttributes<HTMLImageElement> {
  src?: string;
  alt?: string;
  fallBack?: string;
  imgSmall?: boolean;
  classes?: string;
  imgCart?: boolean;
  imgString?: string;
}

const Image = forwardRef<HTMLImageElement, ImageProps>(
  ({ src, alt, imgSmall, imgCart, imgString, classes, ...props }, ref) => {
    const [fallBack, setFallBack] = useState("");

    const handleError = () => {
      setFallBack(defaultImage);
    };

    classes += " overflow-hidden object-cover";
    if (imgSmall) {
      classes += " w-22";
    }

    if (imgCart) {
      classes += " w-";
    }
    return (
      <img
        className=" w-24 "
        ref={ref}
        src={imgString ? `/src/assets/img/` + imgString : src}
        alt={alt}
        {...props}
        onError={handleError}
      />
    );
  }
);

export default Image;
